import 'package:flutter_shop/config/index.dart';
import 'package:provide/provide.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(KString.homeTitle),//首页
    );
  }

}
