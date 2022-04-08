import {createStackNavigator} from '@react-navigation/stack';
import {createDrawerNavigator} from '@react-navigation/drawer';
import {NavigationContainer} from '@react-navigation/native';
import {Icon} from '@rneui/base';

import HomePage from '../pages/HomePage';
import AdminPage from '../pages/AdminPage';
import UserPage from '../pages/UserPage';

const ListStack = createStackNavigator(
  {
    Admin: {screen: AdminPage},
    User: {screen: UserPage},
  },
  {
    initialRouteName: 'Admin',
  },
);

const Menu = createDrawerNavigator(
  {
    Home: {
      screen: HomePage,
      navigationOptions: ({navigation}) => ({
        drawerIcon: ({tintColor}) => <Icon name="home" color={tintColor} />,
      }),
    },
    Admin: {
      screen: ListStack,
      navigationOptions: ({navigation}) => ({
        drawerIcon: ({tintColor}) => <Icon name="person" color={tintColor} />,
      }),
    },
  },
  {
    drawerWidth: 300,
    drawerPosition: 'left',
    initialRouteName: 'Home',
  },
);

const AdminMenuStack = NavigationContainer(Menu);
export default AdminMenuStack;
