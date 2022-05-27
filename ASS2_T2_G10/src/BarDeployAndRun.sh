echo "Transfering data to the bar node."
sshpass -f password ssh sd2010@l040101-ws01.ua.pt 'mkdir -p test/theRestaurant'
sshpass -f password ssh sd2010@l040101-ws01.ua.pt 'rm -rf test/theRestaurant/*'
sshpass -f password scp dirBar.zip sd2010@l040101-ws01.ua.pt:test/theRestaurant
echo "Decompressing data sent to the bar node."
sshpass -f password ssh sd2010@l040101-ws01.ua.pt 'cd test/theRestaurant ; unzip -uq dirBar.zip'
echo "Executing program at the bar node."
sshpass -f password ssh sd2010@l040101-ws01.ua.pt 'cd test/theRestaurant/dirBar ; java serverSide.main.ServerRestaurantBar 22001 l040101-ws08.ua.pt 22000'
