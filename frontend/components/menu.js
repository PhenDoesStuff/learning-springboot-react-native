import react from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { createDrawerNavigator } from "@react-navigation/drawer";
import { NavigationContainer } from "@react-navigation/native";
import { Icon } from "@rneui/base";

import HomePage from '../pages/HomePage';
import ProductPage from '../pages/ProductPage';
import DetailPage from '../pages/DetailPage'

const ListStack = createStackNavigator({
  Product: {screen: ProductPage},
  Detail: {screen: DetailPage},
},
{
  initialRouteName: 'Product'
});

const Menu = createDrawerNavigator(
  {
    "Home": {
      screen: HomePage,
      navigationOptions: ({navigation}) => ({
        drawerIcon: ({tintColor}) => (
          <Icon name="home" color={tintColor} />
        )
      }),
    },
    "Product": {
      screen: ListStack,
      navigationOptions: ({navigation}) => ({
        drawerIcon: ({tintColor}) => (
          <Icon name="local-grocery-store" color={tintColor} />
        ),
      }),
    }
  },
  {
    drawerWidth: 300,
    drawerPosition: 'left',
    initialRouteName: 'Home'
  }
);

const MenuStack = NavigationContainer(Menu)
export default MenuStack;