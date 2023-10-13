package amaroke.projet_cm.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final IndexSearcher indexSearcher;
    private final IndexWriter indexWriter;
    private final QueryParser livreQueryParser;
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
        this.livreQueryParser = new QueryParser("comment", new StandardAnalyzer());
        this.indexWriter = new IndexWriter(index, new IndexWriterConfig(analyzer));
    }

    public void indexLivre(String content, Integer id) {
        try {
            Document document = new Document();
            document.add(new TextField("content", content, Field.Store.YES));
            document.add(new StringField("id", id.toString(), Field.Store.YES));
            this.indexWriter.addDocument(document);
            this.indexWriter.commit();
            this.indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
