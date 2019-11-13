import 'package:flutter_shop/config/index.dart';
import 'package:provide/provide.dart';
import 'package:flutter/material.dart';

class CategoryPage extends StatefulWidget {
  _CategoryPageState createState() => _CategoryPageState();
}

class _CategoryPageState extends State<CategoryPage> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(KString.categoryTitle),//分类
    );
  }

}
