package cn.jaminye;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        // RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("10.211.55.26", 9200, "http"),
        //         new HttpHost("10.211.55.29", 9200, "http"),
        //         new HttpHost("10.211.55.30", 9200, "http"));
        // RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        // IndexRequest indexRequest = new IndexRequest("user");
        // User user = new User();
        // user.setUserId("1");
        // user.setUserName("测试");
        // user.setAge(1);
        // indexRequest.id(user.getUserId());
        // indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        // IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        // System.out.println(indexResponse.getResult());

        RestClient restClient = RestClient.builder(new HttpHost("10.211.55.26", 9200, "http"),
                new HttpHost("10.211.55.29", 9200, "http"),
                new HttpHost("10.211.55.30", 9200, "http")).build();
        RestClientTransport restClientTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(restClientTransport);
        //创建索引
        // CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(builder -> builder.index("user"));
        // 插入doc
        // Map<String, Object> map = new HashMap<>();
        // map.put("userName", "test");
        // map.put("age", "12");
        // map.put("id", 1);
        // elasticsearchClient.create(CreateRequest.of(e -> e.index("user").id("1").document(map)));
        SearchRequest searchRequest = SearchRequest.of(builder -> builder.index("user").query(builder1 -> builder1.term(builder2 -> builder2.field("userName").value("test"))));
        SearchResponse<Object> search = elasticsearchClient.search(searchRequest, Object.class);
        ArrayList<Object> objects = new ArrayList<>();
        if (search.hits() != null) {
            List<Hit<Object>> list = search.hits().hits();
            for (Hit<Object> hit :
                    list) {
                Object t = (Object) hit.source();
                objects.add(t);
            }
        }
        System.out.println(objects);
    }
}
