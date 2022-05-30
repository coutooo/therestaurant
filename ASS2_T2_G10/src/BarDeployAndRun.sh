echo "Transfering data to the bar node."
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'mkdir -p Restaurant'
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'rm -rf Restaurant/*'
sshpass -f password scp dirBar.zip sd210@l040101-ws01.ua.pt:Restaurant
echo "Decompressing data sent to the bar node."
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'cd Restaurant ; unzip -uq dirBar.zip'
echo "Executing program at the bar repository."
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'cd Restaurant/dirBar ; java serverSide.main.ServerRestaurantBar 22111 l040101-ws08.ua.pt 22113 l040101-ws02.ua.pt 22112'
echo "Bar server shutdown."