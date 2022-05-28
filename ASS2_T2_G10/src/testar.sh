cd /home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/
javac -cp /home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar */*.java */*/*.java



 gnome-terminal --title="ServerRestaurantGeneralRepos" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantGeneralRepos 22169 
 gnome-terminal --title="ServerRestaurantBar" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantBar 22160 127.0.0.1 22169 127.0.0.1 22162 
 gnome-terminal --title="ServerRestaurantKitchen" -- java  -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantKitchen 22161 127.0.0.1 22169 
 gnome-terminal --title="ServerRestaurantTable" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantTable 22162 127.0.0.1 22169 
  
 sleep 1 
 gnome-terminal --title="WaiterMain" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar clientSide.main.WaiterMain 127.0.0.1 22160 127.0.0.1 22162 127.0.0.1 22161 127.0.0.1 22169 logger 
 gnome-terminal --title="ChefMain" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar clientSide.main.ChefMain 127.0.0.1 22160 127.0.0.1 22161
 sleep 1 
 gnome-terminal --title="StudentMain" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar clientSide.main.StudentMain 127.0.0.1 22160 127.0.0.1 22169
