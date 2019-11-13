import 'package:flutter/material.dart';
import 'package:flutter_shop/pages/index_page.dart';
import 'package:flutter_shop/provide/current_index_provide.dart';
import './config/index.dart';
import 'package:provide/provide.dart';

void main() {
  var currentIndexProvide = CurrentIndexProvide();
  var providers = Providers();
  providers..provide(Provider<CurrentIndexProvide>.value(currentIndexProvide));

  runApp(ProviderNode(child: MyApp(), providers: providers,));
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Container(
      child: MaterialApp(
        title: KString.mainTitle,//静姐商城
        debugShowCheckedModeBanner: false,
        //定制主题
        theme: ThemeData(
          primarySwatch: KColor.primaryColor,
        ),
        home: IndexPage(),
      ),
    );
  }
}
