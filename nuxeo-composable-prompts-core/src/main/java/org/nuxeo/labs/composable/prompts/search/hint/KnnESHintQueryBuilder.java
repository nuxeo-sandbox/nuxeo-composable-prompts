package org.nuxeo.labs.composable.prompts.search.hint;

import org.nuxeo.ecm.core.query.sql.model.EsHint;
import org.nuxeo.elasticsearch.api.ESHintQueryBuilder;
import org.nuxeo.labs.composable.prompts.search.os.KnnQueryBuilder;
import org.opensearch.index.query.QueryBuilder;

import java.util.Arrays;

public class KnnESHintQueryBuilder implements ESHintQueryBuilder {

    @Override
    public QueryBuilder make(EsHint hint, String fieldName, Object value) {
        double[] vector = Arrays.stream(((Object[])value)).mapToDouble(s -> Double.parseDouble((String)s)).toArray();
        return new KnnQueryBuilder(fieldName, vector);
    }
}
