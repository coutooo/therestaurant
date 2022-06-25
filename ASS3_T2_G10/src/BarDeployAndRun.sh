echo "Transfering data to the bar node."
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'mkdir -p test/Restaurant'
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'rm -rf test/Restaurant/*'
sshpass -f password scp dirBar.zip sd210@l040101-ws01.ua.pt:test/Restaurant
echo "Decompressing data sent to the bar node."
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'cd test/Restaurant ; unzip -uq dirBar.zip'
echo "Executing program at the bar node."
sshpass -f password ssh sd210@l040101-ws01.ua.pt 'cd test/Restaurant/dirBar ; ./bar_com_d.sh sd210'
