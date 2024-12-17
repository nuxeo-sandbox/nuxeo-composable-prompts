package org.nuxeo.labs.composable.prompts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.elasticsearch.api.ESHintQueryBuilder;
import org.nuxeo.elasticsearch.api.ElasticSearchAdmin;
import org.nuxeo.elasticsearch.api.ElasticSearchService;
import org.nuxeo.elasticsearch.query.NxQueryBuilder;
import org.nuxeo.elasticsearch.test.RepositoryElasticSearchFeature;
import org.nuxeo.labs.composable.prompts.search.hint.KnnESHintQueryBuilder;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import jakarta.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(FeaturesRunner.class)
@Features({PlatformFeature.class, RepositoryElasticSearchFeature.class})
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Deploy("nuxeo-composable-prompts-core")
public class TestKnnEsHint {

    @Inject
    protected CoreSession session;

    @Inject
    protected ElasticSearchAdmin elasticSearchAdmin;

    @Inject
    protected ElasticSearchService ess;

    @Test
    public void shouldRetrieveESHintQueryBuilderWithoutException() {
        Optional<ESHintQueryBuilder> knnQueryBuilder = elasticSearchAdmin.getHintByOperator("knn");
        assertTrue(knnQueryBuilder.isPresent());
        assertTrue(knnQueryBuilder.get() instanceof KnnESHintQueryBuilder);
    }

    @Test(expected = NuxeoException.class)
    public void testHint() {
        String nxql = "SELECT * FROM Document WHERE /*+ES: INDEX(embedding:value) OPERATOR(knn) */ embedding:value IN (0.5, 1)";
        DocumentModelList ret = ess.query(new NxQueryBuilder(session).nxql(nxql));
    }

}
