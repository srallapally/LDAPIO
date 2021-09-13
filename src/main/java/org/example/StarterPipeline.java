/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example;

import com.forgerock.connector.beam.TestIO;
import com.forgerock.connector.beam.TestRowMapper;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.coders.ListCoder;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class StarterPipeline {

  private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

  public static void main(String[] args) {

    PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
    Pipeline p = Pipeline.create(options);
    String outputPath = new File("src/main/resources/beam_output/").getPath();
    p.apply(TestIO.<String>read().withRowMapper(new TestIO.RowMapper<String>(){
                                              @Override
                                              public String mapRow(String a) throws Exception {
                                              return a;
                                              }
                                  })
                                 .withCoder(StringUtf8Coder.of())
           ).apply(TextIO.write().to(outputPath + "/output-").withNumShards(2));

    //p.apply(read()).apply(TextIO.write().to(outputPath + "/output-").withNumShards(2));
    p.run().waitUntilFinish();
  }
  private static PTransform<PBegin, PCollection<String>> read() {
    final List<String> LINES = Arrays.asList(
            "To be, or not to be: that is the question: ",
            "Whether 'tis nobler in the mind to suffer ",
            "The slings and arrows of outrageous fortune, ",
            "Or to take arms against a sea of troubles, ");
    return new PTransform<PBegin, PCollection<String>>() {

      @Override
      public PCollection<String> expand(PBegin input) {
        return input.apply(UUID.randomUUID().toString(),Create.of(LINES)).setCoder(StringUtf8Coder.of());
      }
    };
  }

  /*
  public static void main(String[] args) {
    final List<String> LINES = Arrays.asList(
            "To be, or not to be: that is the question: ",
            "Whether 'tis nobler in the mind to suffer ",
            "The slings and arrows of outrageous fortune, ",
            "Or to take arms against a sea of troubles, ");
    PipelineOptions options = PipelineOptionsFactory.create();
    Pipeline pipeline = Pipeline.create(options);
    PCollection<String> input = pipeline.apply(Create.of(LINES)).setCoder(StringUtf8Coder.of());
    String outputPath = new File("src/main/resources/beam_output/").getPath();
//You can control the number of output by specify the using .withNumShards(2)
    input.apply(TextIO.write().to(outputPath + "/output-").withNumShards(2));
    pipeline.run().waitUntilFinish();
    System.out.println(input.isBounded());
  }

   */
}