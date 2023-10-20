package amaroke.tpnote.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import amaroke.tpnote.model.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final IndexSearcher indexSearcher;
    private final IndexWriter indexWriter;
    private final FSDirectory index;

    public IndexService() throws IOException {
        String indexDirectoryPath = "index";
        Path path = Paths.get(indexDirectoryPath);
        this.index = FSDirectory.open(path);
        StandardAnalyzer analyzer = new StandardAnalyzer();
        if (!DirectoryReader.indexExists(index)) {
            IndexWriterConfig initConfig = new IndexWriterConfig(analyzer);
            try (IndexWriter writer = new IndexWriter(index, initConfig)) {
            }
        }
        this.indexSearcher = new IndexSearcher(DirectoryReader.open(index));
        this.indexWriter = new IndexWriter(index, new IndexWriterConfig(analyzer));
    }

    public void indexRestaurant(RestaurantEntity restaurant) {
        try {
            Document document = new Document();
            document.add(new TextField("nom", restaurant.getNom(), Field.Store.YES));
            document.add(new StringField("id", "restaurant" + restaurant.getId().toString(), Field.Store.YES));
            String tags = String.join(" ",
                    restaurant.getTags().stream().map(Enum::name).toArray(String[]::new));
            document.add(new TextField("tags", tags, Field.Store.YES));
            if (restaurant.getMoyenne() != null) {
                document.add(new FloatPoint("noteMoyenne", restaurant.getMoyenne()));
            }
            this.indexWriter.addDocument(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> searchRestaurantIds(String keyword, Float minNote, Float maxNote, String tag) {
        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
        try {
            if (keyword != null) {
                queryBuilder.add(new QueryParser("nom", new StandardAnalyzer()).parse(keyword),
                        BooleanClause.Occur.MUST);
            }

            if (minNote != null && maxNote != null) {
                queryBuilder.add(FloatPoint.newRangeQuery("noteMoyenne", minNote, maxNote), BooleanClause.Occur.MUST);
            }

            if (tag != null) {
                queryBuilder.add(new QueryParser("tags", new StandardAnalyzer()).parse(tag), BooleanClause.Occur.MUST);
            }

            TopDocs topDocs = indexSearcher.search(queryBuilder.build(), 10);

            return Arrays.stream(topDocs.scoreDocs)
                    .map(scoreDoc -> {
                        try {
                            String id = this.indexSearcher.storedFields()
                                    .document(scoreDoc.doc)
                                    .getField("id")
                                    .stringValue();
                            return Integer.parseInt(id.replace("restaurant", ""));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
