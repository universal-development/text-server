# text-server
Service for storing texts in one place 

## Build

Compile and tests execution

`./gradlew clean build`

Docker image build

`docker build . -t text-server:latest`

Docker container launching from docker image:

`docker run -i -t --rm  -p 8080:8080 -v $(pwd)/storage:/storage text-server:latest`

Swagger UI will be available on: `http://localhost:8080/swagger-ui.html`

# Text lines client

Codegen CLI:

```
java -jar swagger-codegen-cli.jar generate \
  -i http://localhost:8080/v2/api-docs \
  --api-package com.unidev.textlines.client.api \
  --model-package com.unidev.textlines.petstore.client.model \
  --invoker-package com.unidev.textlines.client.invoker \
  --group-id com.unidev \
  --artifact-id textlines-client \
  --artifact-version 0.0.1-SNAPSHOT \
  -l java \
  --additional-properties java8=true \
  --library resttemplate \
  -o textlines-client 
 ```


### Links

Swagger codegen:
`https://search.maven.org/classic/remotecontent?filepath=io/swagger/swagger-codegen-cli/2.2.3/swagger-codegen-cli-2.2.3.jar`

Swagger api docs:
`http://localhost:8080/v2/api-docs`

License
=======
 
    Copyright (c) 2017 Denis O <denis.o@linux.com>
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
