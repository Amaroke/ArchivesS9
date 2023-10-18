package amaroke.exofinal.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final IndexSearcher indexSearcher;
    private final IndexWriter indexWriter;
    private final QueryParser serieQueryParser;
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
        this.serieQueryParser = new QueryParser("comment", new StandardAnalyzer());
        this.indexWriter = new IndexWriter(index, new IndexWriterConfig(analyzer));
    }

    public void indexDescription(String description, Integer serieId) {
        try {
            Document document = new Document();
            document.add(new TextField("description", description, Field.Store.YES));
            document.add(new StringField("id", "serie" + serieId.toString(), Field.Store.YES));
            this.indexWriter.addDocument(document);
            this.indexWriter.commit();
            this.indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void indexCommentaire(String commentaire, Integer commentaireId) {
        try {
            Document document = new Document();
            document.add(new TextField("commentaire", commentaire, Field.Store.YES));
            document.add(new StringField("id", "commentaire" + commentaireId.toString(), Field.Store.YES));
            this.indexWriter.addDocument(document);
            this.indexWriter.commit();
            this.indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> searchDescription(String contentToSearch) {
        Query query;
        TopDocs topDocs;

        try {
            query = this.serieQueryParser.parse(contentToSearch);
            topDocs = this.indexSearcher.search(query, 10);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return Arrays.stream(topDocs.scoreDocs)
                .map(scoreDoc -> {
                    try {
                        return this.indexSearcher.storedFields()
                                .document(scoreDoc.doc)
                                .getField("id")
                                .stringValue();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public List<String> searchCommentaire(String contentToSearch) {
        Query query;
        TopDocs topDocs;

        try {
            query = this.serieQueryParser.parse(contentToSearch);
            topDocs = this.indexSearcher.search(query, 10);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return Arrays.stream(topDocs.scoreDocs)
                .map(scoreDoc -> {
                    try {
                        return this.indexSearcher.storedFields()
                                .document(scoreDoc.doc)
                                .getField("id")
                                .stringValue();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

}
