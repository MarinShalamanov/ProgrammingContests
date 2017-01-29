#include <iostream>
#include <string>
#include <sstream>
#include <istream>
#include <fstream>
using namespace std;

const int LOG_LEVEL = 2;
const int BLACK_BELOW = 50;

int data[30][60];
int columnData[60];

int n, m;

class CharImage {
public:
	int w;
    int data[10][10];

    void print() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < w; j++) {
                cout << data[i][j] << " ";
            }
            cout << endl;
        }
		cout << endl;
    }
} charTemplates[256];

void read(istream &in) {
    in >> n >> m;
    for(int i = 0; i < 30; i++) {
        for(int j = 0; j < 60; j++) {
            string pixel;
            in >> pixel;

            for(int k = 0; k < pixel.length(); k++) {
                if(pixel[k]==',') {
                    pixel[k] = ' ';
                }
            }

            stringstream ss(pixel);
            int red, green, blue;
            ss >> red >> green >> blue;

            if(red < BLACK_BELOW && green < BLACK_BELOW && blue < BLACK_BELOW) {
                data[i][j] = 1;
            } else {
                data[i][j] = 0;
            }
            //ss >> data[i][j][0] >>  data[i][j][1] >>  data[i][j][2];
        }
    }
}

void log(string s, int level=0) {
	if (level < LOG_LEVEL) {
		cout << s << endl;
	}
}


void computeColumnData() {
    for(int j = 0; j < 60; j++) {
        int columnValue = 0;
        for(int i = 0; i < 30; i++) {
            if(data[i][j]==1) {
                columnValue = 1;
                break;
            }
        }

        columnData[j] = columnValue;
    }
}

void extractFirstCharImage(CharImage& img) {
    int up = 11;
    int down = 20;
    int left, right;

    for(int j = 0; j < 60; j++) {
        if(columnData[j]==1) {
            left = j;
            break;
        }
    }

    for(int j = left+1; j < 60; j++) {
        if(columnData[j]==0) {
            right = j-1;
            break;
        }
    }

	log("column data");
	for(int i = 0; i < 60; i++) cout << columnData[i] << " ";
	cout << endl;
	
	log("char ex: " + to_string(up) + " " + to_string(down) +
				" " + to_string(left) + 
				" " + to_string(right));
	
	img.w = right-left+1;
    for(int i = up; i <= down; i++) {
        for(int j = left; j <= right; j++) {
            img.data[i-up][j-left] = data[i][j];
        }
    }

    // delete the char from column data
    for(int j = left; j <= right; j++) {
        columnData[j]=0;
    }
}

void printData() {
	 for(int i = 0; i < 30; i++) {
        for(int j = 0; j < 60; j++) {
			cout << data[i][j] << " ";
		}
		cout << endl;
	 }
	 cout << endl;
}



void proccessSample(int sampleId) {
    const string inputPref = "input/input";
    const string outputPref = "output/output";
	const string suffix = ".txt";
	
	
	string num;
	if(sampleId < 10) num = "0";
	num += to_string(sampleId);
    string id = inputPref + num;
	
	string inFileName = inputPref + num + suffix;
	string outFileName = outputPref + num + suffix;
	
	ifstream inFile, outFile;
	inFile.open(inFileName);
	outFile.open(outFileName);
	
	log("openning file " + inFileName);
	log("openning file " + outFileName);
	
	string outWord;
	outFile >> outWord;
	
	log("output word is " + outWord);
	
	read(inFile);
	computeColumnData();
	
	inFile.close();
	outFile.close();
	
	printData();
	
	//CharImage img;
	for(int i = 0; i < 5; i++) {
		extractFirstCharImage(charTemplates[outWord[i]]);
		//img.print();
	}
}
 
void processAll() {
	for(int sample = 0; sample < 25; sample++) {
		proccessSample(sample);
	}
}

void printTemplates() {
	for(char c = 'A'; c <= 'Z'; c++) {
		log(string(1, c));
		log(to_string(charTemplates[c].w));
		charTemplates[c].print();
	}
	
	for(char c = '0'; c <= '9'; c++) {
		log(string(1, c));
		log(to_string(charTemplates[c].w));
		charTemplates[c].print();
	}
}

bool compare(const CharImage& a, const CharImage& b) {
	//if(a.w != b.w) return false;
	int w = min(a.w, b.w);
	
	int count = 0;
	for(int i = 0; i < 10; i++) {
		for(int j = 0; i < w; j++) {
			if(a.data[i][j] == b.data[i][j]) {
				count++;
			}
		}
	}
	
	return count > 30;
}

char recognizeCharImg(CharImage& img) {
	for(char c = 'A'; c <= 'Z'; c++) {
		CharImage& templ = charTemplates[c];
		if(compare(img, templ)) {
			return c;
		}
		
	}
	
	for(char c = '0'; c <= '9'; c++) {
		CharImage& templ = charTemplates[c];
		if(compare(img, templ)) {
			return c;
		}
	}
	
	return '?';
}

void recognizeSample(int sampleId) {
    const string inputPref = "input/input";
    const string outputPref = "output/output";
	const string suffix = ".txt";
	
	
	string num;
	if(sampleId < 10) num = "0";
	num += to_string(sampleId);
    string id = inputPref + num;
	
	string inFileName = inputPref + num + suffix;
	
	ifstream inFile;
	inFile.open(inFileName);
	
	log("openning file " + inFileName);
		
	read(inFile);
	computeColumnData();
	
	inFile.close();
	
	// printData();
	
	CharImage img;
	for(int i = 0; i < 5; i++) {
		extractFirstCharImage(img);
		char c = recognizeCharImg(img);
		cout << c << endl;
		img.print();
	}
}

int main() {
    //proccessSample(4);
	processAll();
	printTemplates();

	recognizeSample(24);
    //read(cin);
    //computeColumnData();


    return 0;
}
