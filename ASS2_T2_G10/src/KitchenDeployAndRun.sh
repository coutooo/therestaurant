echo "Transfering data to the kitchen node."
sshpass -f password ssh sd210@l040101-ws03.ua.pt 'mkdir -p Restaurant'
sshpass -f password ssh sd210@l040101-ws03.ua.pt 'rm -rf Restaurant/*'
sshpass -f password scp dirKitchen.zip sd210@l040101-ws03.ua.pt:Restaurant
echo "Decompressing data sent to the kitchen node."
sshpass -f password ssh sd210@l040101-ws03.ua.pt 'cd Restaurant ; unzip -uq dirKitchen.zip'
echo "Executing program at the kitchen server."
sshpass -f password ssh sd210@l040101-ws03.ua.pt 'cd Restaurant/dirKitchen ; java serverSide.main.ServerRestaurantKitchen 22110 l040101-ws08.ua.pt 22113'
echo "Kitchen server shutdown."