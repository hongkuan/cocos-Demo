import 'package:flutter/material.dart';

//切换底部导航栏
class CurrentIndexProvide with ChangeNotifier {
  int currentIndex = 0;

  void changeIndex(int newIndex){
    this.currentIndex = newIndex;
    notifyListeners();
  }
}