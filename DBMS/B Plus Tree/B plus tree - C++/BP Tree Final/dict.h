#include<bits/stdc++.h>
using namespace std;


struct Dictionary {
    Dictionary()
    {
        std::ifstream dict ("Translation.txt");
        for(std::string line; getline(dict, line);){
            _words.insert(line);
            string ts = line.substr(1);
            int pos = ts.find("|");
            string word = ts.substr(0,pos);
            string meaning = ts.substr(pos+1);
            dictionary.insert({word,meaning});
        }
    }

    bool contains(std::string const& word) const {return _words.count(word);}

    std::set<std::string> _words;
    map<string, string> dictionary;
};
