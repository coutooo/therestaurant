cd /home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/
javac -cp /home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar */*.java */*/*.java



gnome-terminal --title="ServerRestaurantGeneralRepos" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantGeneralRepos 22110 
gnome-terminal --title="ServerRestaurantBar" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantBar 22111 127.0.0.1 22110 127.0.0.1 22112
gnome-terminal --title="ServerRestaurantKitchen" -- java  -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantKitchen 22113 127.0.0.1 22110 
gnome-terminal --title="ServerRestaurantTable" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar serverSide.main.ServerRestaurantTable 22112 127.0.0.1  22110

sleep 1 
gnome-terminal --title="WaiterMain" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar clientSide.main.WaiterMain 127.0.0.1 22113 127.0.0.1 22111 127.0.0.1 22112 127.0.0.1 22110
gnome-terminal --title="ChefMain" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar clientSide.main.ChefMain  127.0.0.1 22113 127.0.0.1 22111 127.0.0.1 22110
sleep 1 
gnome-terminal --title="StudentMain" -- java -cp .:/home/couto/Desktop/Universidade/therestaurant/ASS2_T2_G10/src/genclass.jar clientSide.main.StudentMain 127.0.0.1 22111 127.0.0.1 22112 127.0.0.1 22110