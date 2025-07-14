# thesis

### Instructions setup Flink, Prometheus and Grafana infrastructure:
1. Start the local Flink cluster:

    First, navigate to the Flink directory (e.g. `$ cd ~/flink-1.20.0`)
  
    Then, execute:
  
       $ ./bin/start-cluster.sh
   
   Now the Flink dashboard is exposed on: *http://localhost:8081/#/overview*.

2. Start the Prometheus server:

   First, navigate to the Prometheus directory (e.g. `$ cd ~/prometheus-3.3.0.linux-amd64`)

   Then, execute:
   
        $ ./prometheus --config.file=prometheus.yml

   Now Prometheus server is exposed on: *http://localhost:9090/query*.

3. Start Grafana:
   
        $ sudo service grafana-server start
   
   Then, Grafana is exposed on: *http://localhost:3000/*.
   
   Username: *admin*
   
   Password: *admin*

### Instructions to make changes, compile, package and run Flink applications:

1. Open Intellij IDEA and navigate to the directory: `thesis-root/thesis/FlinkStreamJob`
2. Make code changes.
3. Navigate to the above directory and run:

       $ mvn clean package
4. Then, in order to run the Flink application on the Flink cluster, execute:

       $ flink run -c org.ntua.FlinkStreamJob.Main target/FlinkStreamJob-1.0-SNAPSHOT.jar

5. Now, the application must be running. Please check its progress on the [Apache Flink Dashboard](http://localhost:8081/#/overview).


