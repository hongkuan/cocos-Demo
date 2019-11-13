import 'package:flutter_shop/config/index.dart';
import 'package:provide/provide.dart';
import 'package:flutter/material.dart';

class MemberPage extends StatefulWidget {
  _MemberPageState createState() => _MemberPageState();
}

class _MemberPageState extends State<MemberPage> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(KString.memberTitle),//会员中心
    );
  }

}
