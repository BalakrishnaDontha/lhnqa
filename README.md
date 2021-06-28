# Execution Commands

Please execute below commands to run the suite.
 1) Download grawdle 5.4 at local machine --> https://tecadmin.net/install-gradle-centos-7/  
 
 2) Build project : "gradle buildFatJar" (or) use Jenkins for CI/CD
 
 3) Create local folder 'test-output' to map to suite output

 4) Copy 'Dockerfile' outside of 'lhnqa' folder to build and run
 
 5) Run docker command : docker run -it -v /home/ec2-user/automation/LHN/lhnqa/test-output:/automation/lhnqa/test-output dgoud405/lhnqa:2.0

 6) Copy local folder 'test-output\emailable-report.html' to S3. Download from there for results
