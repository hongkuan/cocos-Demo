#include "spreadsheet.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	spreadsheet w;
	w.show();
	return a.exec();
}
