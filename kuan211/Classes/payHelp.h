//
//  XGWin32Help.h
//  XGDemo
//
//  Created by zhangdaochuan on 15/10/27.
//
//

#ifndef XGWin32Help_h
#define XGWin32Help_h

#include <stdio.h>
#include <string>
#include "cocos2d.h"

using namespace std;


#define CC_SAFE_DELETE(p)            do { if(p) { delete (p); (p) = 0; } } while(0)
class PayHelp {
public:
	static PayHelp& getInstance()
    {
        static PayHelp    instance; // Guaranteed to be destroyed. Instantiated on first use.
        return instance;
    }
    ~PayHelp();
	 
	void setPayQuantity(const char* _sPayQuantity);
	
	void setPayPrice(const char* _spayPrice);

	void setPayId(const char* _spayId);

private:
	 PayHelp();
	 char* _payQuantity ;
	 char* _payPrice;
	 char* _payId;
};

#endif /* XGWin32Help_h */
