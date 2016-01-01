package io.dongyue.at15.tree.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;

/**
 * Created by at15 on 16-1-2.
 *
 */
@Configuration
@EnableHadoop
public class Config extends SpringHadoopConfigurerAdapter {

    @Override
    public void configure(HadoopConfigConfigurer config) throws Exception {
        config.fileSystemUri("hdfs://localhost:9000");
    }

}
