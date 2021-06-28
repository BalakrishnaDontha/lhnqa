FROM openjdk:12

RUN mkdir /automation

COPY ./lhnqa /automation/lhnqa

WORKDIR /automation/lhnqa/build/libs 

CMD ["java","-jar", "LHN-QA-Fat-jar-0.0.1.jar",   "/automation/lhnqa/Resources/testNG_LHN_API.xml"]
