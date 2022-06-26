rm -rf dir*/*/
echo "Compiling source code."
javac -source 8 -target 8 -cp ../../genclass.jar */*.java */*/*.java
echo "Distributing intermediate code to the different execution environments."
echo "  RMI registry"
rm -rf dirRMIRegistry/interfaces dirRMIRegistry/commInfra 
mkdir -p dirRMIRegistry/interfaces dirRMIRegistry/commInfra 
cp interfaces/*.class dirRMIRegistry/interfaces
cp commInfra/*.class dirRMIRegistry/commInfra 
cp set_rmiregistry_d.sh dirRMIRegistry
echo "  Register Remote Objects"
rm -rf dirRegistry/serverSide dirRegistry/interfaces dirRegistry/commInfra 
mkdir -p dirRegistry/serverSide dirRegistry/serverSide/main dirRegistry/serverSide/objects dirRegistry/interfaces dirRegistry/commInfra 
cp serverSide/main/ServerRegisterRemoteObject.class dirRegistry/serverSide/main
cp serverSide/objects/RegisterRemoteObject.class dirRegistry/serverSide/objects
cp interfaces/Register.class dirRegistry/interfaces
cp commInfra/*.class dirRegistry/commInfra 
cp registry_com_d.sh dirRegistry
cp java.policy dirRegistry
echo "  General Repository of Information"
rm -rf dirGeneralRepos/serverSide dirGeneralRepos/clientSide dirGeneralRepos/interfaces dirGeneralRepos/commInfra 
mkdir -p dirGeneralRepos/serverSide dirGeneralRepos/serverSide/main dirGeneralRepos/serverSide/objects dirGeneralRepos/interfaces \
         dirGeneralRepos/clientSide dirGeneralRepos/clientSide/entities dirGeneralRepos/commInfra 
cp serverSide/main/ExecConst.class serverSide/main/ServerRestaurantGeneralRepos.class dirGeneralRepos/serverSide/main
cp serverSide/objects/GeneralRepos.class dirGeneralRepos/serverSide/objects
cp interfaces/Register.class interfaces/GeneralReposInterface.class dirGeneralRepos/interfaces
cp clientSide/entities/WaiterState.class clientSide/entities/ChefState.class clientSide/entities/StudentState.class dirGeneralRepos/clientSide/entities
cp commInfra/*.class dirGeneralRepos/commInfra 
cp java.policy dirGeneralRepos
cp repos_com_d.sh dirGeneralRepos
echo "  Bar"
rm -rf dirBar/serverSide dirBar/clientSide dirBar/interfaces dirBar/commInfra
mkdir -p dirBar/serverSide dirBar/serverSide/main dirBar/serverSide/objects dirBar/interfaces \
         dirBar/clientSide dirBar/clientSide/entities dirBar/commInfra
cp serverSide/main/ExecConst.class serverSide/main/ServerRestaurantBar.class dirBar/serverSide/main
cp serverSide/objects/Bar.class serverSide/objects/Table.class serverSide/objects/Kitchen.class dirBar/serverSide/objects
cp interfaces/*.class dirBar/interfaces
cp clientSide/entities/WaiterState.class clientSide/entities/StudentState.class clientSide/entities/ChefState.class dirBar/clientSide/entities
cp commInfra/*.class dirBar/commInfra
cp bar_com_d.sh dirBar
cp java.policy dirBar
echo "  Table"
rm -rf dirTable/serverSide dirTable/clientSide dirTable/interfaces dirTable/commInfra
mkdir -p dirTable/serverSide dirTable/serverSide/main dirTable/serverSide/objects dirTable/interfaces \
         dirTable/clientSide dirTable/clientSide/entities dirTable/commInfra
cp serverSide/main/ExecConst.class serverSide/main/ServerRestaurantTable.class dirTable/serverSide/main
cp serverSide/objects/Table.class dirTable/serverSide/objects
cp interfaces/*.class dirTable/interfaces
cp clientSide/entities/WaiterState.class clientSide/entities/StudentState.class dirTable/clientSide/entities
cp commInfra/*.class dirTable/commInfra
cp table_com_d.sh dirTable
cp java.policy dirTable
echo "  Kitchen"
rm -rf dirKitchen/serverSide dirKitchen/clientSide dirKitchen/interfaces dirKitchen/commInfra
mkdir -p dirKitchen/serverSide dirKitchen/serverSide/main dirKitchen/serverSide/objects dirKitchen/interfaces \
         dirKitchen/clientSide dirKitchen/clientSide/entities dirKitchen/commInfra
cp serverSide/main/ExecConst.class serverSide/main/ServerRestaurantKitchen.class dirKitchen/serverSide/main
cp serverSide/objects/Kitchen.class dirKitchen/serverSide/objects
cp interfaces/*.class dirKitchen/interfaces
cp clientSide/entities/WaiterState.class clientSide/entities/ChefState.class dirKitchen/clientSide/entities
cp commInfra/*.class dirKitchen/commInfra
cp kitchen_com_d.sh dirKitchen
cp java.policy dirKitchen
echo "  Chef"
rm -rf dirChef/serverSide dirChef/clientSide dirChef/interfaces dirChef/commInfra
mkdir -p dirChef/serverSide dirChef/serverSide/main dirChef/clientSide dirChef/clientSide/main dirChef/clientSide/entities \
         dirChef/interfaces dirChef/commInfra
cp serverSide/main/ExecConst.class dirChef/serverSide/main
cp clientSide/main/ClientRestaurantChef.class dirChef/clientSide/main
cp clientSide/entities/Chef.class clientSide/entities/ChefState.class dirChef/clientSide/entities
cp interfaces/*.class dirChef/interfaces
cp commInfra/*.class dirChef/commInfra
cp chef_com_d.sh dirChef
echo "  Waiter"
rm -rf dirWaiter/serverSide dirWaiter/clientSide dirWaiter/interfaces dirWaiter/commInfra
mkdir -p dirWaiter/serverSide dirWaiter/serverSide/main dirWaiter/clientSide dirWaiter/clientSide/main dirWaiter/clientSide/entities \
         dirWaiter/interfaces dirWaiter/commInfra
cp serverSide/main/ExecConst.class dirWaiter/serverSide/main
cp clientSide/main/ClientRestaurantWaiter.class dirWaiter/clientSide/main
cp clientSide/entities/Waiter.class clientSide/entities/WaiterState.class dirWaiter/clientSide/entities
cp interfaces/*.class dirWaiter/interfaces
cp commInfra/*.class dirWaiter/commInfra 
cp waiter_com_d.sh dirWaiter
echo "  Student"
rm -rf dirStudent/serverSide dirStudent/clientSide dirStudent/interfaces dirStudent/commInfra
mkdir -p dirStudent/serverSide dirStudent/serverSide/main dirStudent/clientSide dirStudent/clientSide/main dirStudent/clientSide/entities \
         dirStudent/interfaces dirStudent/commInfra
cp serverSide/main/ExecConst.class dirStudent/serverSide/main
cp clientSide/main/ClientRestaurantStudent.class dirStudent/clientSide/main
cp clientSide/entities/Student.class clientSide/entities/StudentState.class dirStudent/clientSide/entities
cp interfaces/*.class dirStudent/interfaces
cp commInfra/*.class dirStudent/commInfra 
cp student_com_d.sh dirStudent
echo "Compressing execution environments."
echo "  Genclass"
rm -f genclass.zip
zip -rq genclass.zip . -i genclass
echo "  RMI registry"
rm -f  dirRMIRegistry.zip
zip -rq dirRMIRegistry.zip dirRMIRegistry
echo "  Register Remote Objects"
rm -f  dirRegistry.zip
zip -rq dirRegistry.zip dirRegistry
echo "  General Repository of Information"
rm -f  dirGeneralRepos.zip
zip -rq dirGeneralRepos.zip dirGeneralRepos
echo "  Bar"
rm -f  dirBar.zip
zip -rq dirBar.zip dirBar
echo "  Kitchen"
rm -f  dirKitchen.zip
zip -rq dirKitchen.zip dirKitchen
echo "  Table"
rm -f  dirTable.zip
zip -rq dirTable.zip dirTable
echo "  Chef"
rm -f  dirChef.zip
zip -rq dirChef.zip dirChef
echo "  Waiter"
rm -f  dirWaiter.zip
zip -rq dirWaiter.zip dirWaiter
echo "  Student"
rm -f  dirStudent.zip
zip -rq dirStudent.zip dirStudent
