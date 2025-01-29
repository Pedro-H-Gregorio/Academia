package repositories;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import entities.Comprovante;
import jdbc.BaseRespository;
import jdbc.interfaces.JDBCRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ComprovanteRepository extends BaseRespository implements JDBCRepository<Comprovante, Integer> {
    @Override
    public int save(Comprovante object) throws SQLException {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("academia");
        MongoCollection<Document> collection = database.getCollection("comprovantes");
        try {
            InsertOneResult insertOneResult = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("id", object.getId())
                    .append("matricula_aluno", object.getMatriculaAluno())
                    .append("mensalidade_id", object.getMensalidadeId())
                    .append("tipo_arquivo", object.getTipoArquivo())
                    .append("nome_arquivo", object.getNomeArquivo())
                    .append("descricao_arquivo", object.getDescricaoArquivo())
                    .append("bucket_arquivo", object.getBucketArquivo()));
            return 1;
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Comprovante update(Comprovante object) throws SQLException {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("academia");
        MongoCollection<Document> collection = database.getCollection("comprovantes");
        Document query = new Document().append("id", object.getId());
        Bson update = Updates.combine(
                Updates.set("matricula_aluno", object.getMatriculaAluno()),
                Updates.set("mensalidade_id", object.getMensalidadeId()),
                Updates.set("tipo_arquivo", object.getTipoArquivo()),
                Updates.set("nome_arquivo", object.getNomeArquivo()),
                Updates.set("descricao_arquivo", object.getDescricaoArquivo()),
                Updates.set("bucket_arquivo", object.getBucketArquivo())
        );
        UpdateOptions options = new UpdateOptions().upsert(true);
        try{
            UpdateResult result = collection.updateOne(query, update, options);
            Comprovante updateResult = this.getById(object.getId());
            return updateResult;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(Integer object) throws SQLException {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("academia");
        MongoCollection<Document> collection = database.getCollection("comprovantes");
        Bson query = eq("id", object);

        try {
            DeleteResult result = collection.deleteOne(query);
            return 1;
        } catch (MongoException me) {
            me.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Comprovante> getAll() {
        List<Comprovante> comprovantes = new ArrayList<>();

        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("academia");
        MongoCollection<Document> collection = database.getCollection("comprovantes");

        Bson projectionFields = Projections.fields(
                Projections.include("id", "matricula_aluno", "mensalidade_id", "tipo_arquivo", "nome_arquivo", "descricao_arquivo", "bucket_arquivo"),
                Projections.excludeId()
        );

        FindIterable<Document> docs = collection.find().projection(projectionFields);

        for (Document doc : docs) {
            Comprovante comprovante = new Comprovante(
                    doc.getInteger("id"),
                    doc.getInteger("matricula_aluno"),
                    doc.getInteger("mensalidade_id"),
                    doc.getString("tipo_arquivo"),
                    doc.getString("nome_arquivo"),
                    doc.getString("descricao_arquivo"),
                    doc.getString("bucket_arquivo")
            );
            comprovantes.add(comprovante);
        }

        return comprovantes;
    }

    @Override
    public Comprovante getById(Integer object) throws SQLException {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("academia");
        MongoCollection<Document> collection = database.getCollection("comprovantes");
        Bson projectionFields = Projections.fields(
                Projections.include("id","matricula_aluno", "mensalidade_id", "tipo_arquivo", "nome_arquivo", "descricao_arquivo", "bucket_arquivo"),
                Projections.excludeId());

        Document doc = collection.find(eq("id", object))
                .projection(projectionFields)
                .first();
        if (doc != null){
            Comprovante comprovante = new Comprovante(
                    doc.getInteger("id"),
                    doc.getInteger("matricula_aluno"),
                    doc.getInteger("mensalidade_id"),
                    doc.getString("tipo_arquivo"),
                    doc.getString("nome_arquivo"),
                    doc.getString("descricao_arquivo"),
                    doc.getString("bucket_arquivo"));
            return comprovante;
        }
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("academia");
        MongoCollection<Document> collection = database.getCollection("comprovantes");

        try {
            DeleteResult result = collection.deleteMany(new Document());
            return 1;
        } catch (MongoException me) {
            me.printStackTrace();
        }
        return 0;
    }
}
