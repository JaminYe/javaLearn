package cn.jaminye;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * 布隆过滤器
 *
 * @author Jamin
 * @date 2020/8/4 22:07
 */
public class BloomFilterDemo {


    public static void main(String[] args) {
        //创建布隆过滤器
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000000, 0.001);
        //模拟放值
        for (int i = 0; i < 100000; i++) {
            bloomFilter.put(UUID.randomUUID().toString().replace("-", ""));
        }
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            boolean contain = bloomFilter.mightContain("jamin" + i);
            if (contain) {
                count++;
            }
        }
        System.out.println(count);

    }

}
