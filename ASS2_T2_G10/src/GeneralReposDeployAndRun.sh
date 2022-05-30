echo "Transfering data to the general repository node."
sshpass -f password ssh sd210@l040101-ws08.ua.pt 'mkdir -p Restaurant'
sshpass -f password ssh sd210@l040101-ws08.ua.pt 'rm -rf Restaurant/*'
sshpass -f password scp dirGeneralRepos.zip sd210@l040101-ws08.ua.pt:Restaurant
echo "Decompressing data sent to the general repository node."
sshpass -f password ssh sd210@l040101-ws08.ua.pt 'cd Restaurant ; unzip -uq dirGeneralRepos.zip'
echo "Executing program at the server general repository."
sshpass -f password ssh sd210@l040101-ws08.ua.pt 'cd Restaurant/dirGeneralRepos ; java serverSide.main.ServerRestaurantGeneralRepos 22113'
echo "General repository server shutdown."
sshpass -f password ssh sd210@l040101-ws08.ua.pt 'cd Restaurant/dirGeneralRepos ; less logger'