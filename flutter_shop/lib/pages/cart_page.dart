import 'package:flutter_shop/config/index.dart';
import 'package:provide/provide.dart';
import 'package:flutter/material.dart';

class CartPage extends StatefulWidget {
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(KString.shoppingCartTitle),//购物车
    );
  }

}
