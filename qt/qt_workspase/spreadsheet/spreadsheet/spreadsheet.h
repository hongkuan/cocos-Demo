#ifndef SPREADSHEET_H
#define SPREADSHEET_H

#include <QtWidgets/QMainWindow>
#include "ui_spreadsheet.h"

class spreadsheet : public QMainWindow
{
	Q_OBJECT

public:
	spreadsheet(QWidget *parent = 0);
	~spreadsheet();

private:
	Ui::spreadsheetClass ui;
};

#endif // SPREADSHEET_H
