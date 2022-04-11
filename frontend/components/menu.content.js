import { PureComponent } from "react";
import { Image, ScrollView, View } from "react-native";
import { styles } from '../styles/index.style'

export default class DrawerContent extends PureComponent {

  render() {
    return (
      <View style={styles.softContainer}>
        <View style={styles.drawerContainer}>
          <Image source={require('../imgs/react.png')} style={styles.drawerImage} />
          <Text style={styles.drawerText}></Text>
        </View>
        <ScrollView>
          <DrawerItems {...this.props} />
        </ScrollView>
      </View>
    )
  }
}