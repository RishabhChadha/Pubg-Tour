# PubGTour - A Guide to Start the application.

## Clone The Git Project
On the terminal run the command git clone https://gitlab-cts.stackroute.in/cts-23_assignments/Rishabh.Chadha-PubGTour-java-BoilerPlate.git.
Then run the command git checkout pubg inside the folder that got cloned.


## Create the target folder for the backend service
- Inside the service folders Run the command-

	1) source ./env-variable.sh

	2)mvn install package

-Inside the PubGUI folder run the command-

	1)npm install

	2)ng build --prod

-A new folder dist will be created. Open dist folder,open the folder inside it, copy the files and paste outside.

-Now go to the main folder and run the command sudo docker-compose up --build

-The application will be up and running.

-Access	the application using localhost:4200

