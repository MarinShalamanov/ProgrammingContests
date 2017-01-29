#include <iostream>
#include <string>
#include <sstream>
using namespace std;



int main() {

    string pixel;

    int numPixels = 0;
    int numDarkPixels = 0;
    const double DARK_THRESHOLD = 0.2;

    while(cin >> pixel) {
        numPixels++;

        int firstIdx = 0, secondIdx = 0;

        for(int i = 0; i < pixel.length(); i++) {
            if(pixel[i]==',') {
                pixel[i] = ' ';
            }
        }

        stringstream ss(pixel);

        int red, green, blue;
        ss >> red >> green >> blue;

        double avg = ((red+green+blue)/3.0)/255;
        if (avg < DARK_THRESHOLD) {
            numDarkPixels++;
            //cout << red << " " << green << " " << blue << " is dark\n";
        }


    }

    double procentDark = numDarkPixels / (double) numPixels;

    if(procentDark < 0.5) {
        cout << "day" << endl;
    } else {
        cout << "night" << endl;
    }


    return 0;
}
