package org.nuxeo.labs.composable.prompts.search.os;

import org.apache.lucene.search.Query;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.index.query.AbstractQueryBuilder;
import org.opensearch.index.query.QueryShardContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class KnnQueryBuilder extends AbstractQueryBuilder<KnnQueryBuilder> {

    public static final String NAME = "knn";

    public String field;
    public double[] vector;

    public KnnQueryBuilder(String field, double[] vector) {
        this.field = field;
        this.vector = vector;
    }

    @Override
    protected void doWriteTo(StreamOutput out) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(NAME);
        builder.startObject(field);
        builder.startArray("vector");
        for(double d: vector) {
            builder.value(d);
        }
        builder.endArray();
        builder.field("k",10);
        builder.endObject();
        builder.endObject();
    }

    @Override
    protected Query doToQuery(QueryShardContext context) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean doEquals(KnnQueryBuilder other) {
        return field.equals(other.field) && Objects.deepEquals(vector, other.vector);
    }

    @Override
    protected int doHashCode() {
        return Objects.hash(super.hashCode(), field, Arrays.hashCode(vector));
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }

}
