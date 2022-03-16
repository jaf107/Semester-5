#include <bits/stdc++.h>
#include<locale.h>

using namespace std;

#define MAX 20

typedef struct key_tt
{
    string english, bangla;
}kt;

class node
{
    bool is_leaf;
    key_tt *keys;
    int size;
    node **ptr;
    friend class BPTree;

    public:
    node();
};

node::node()
{
    keys = new key_tt[MAX+1];
    ptr = new node*[MAX + 2];
}

class BPTree
{
    node *root;
    node * findParent(node*, node*);
    void insertInternal(key_tt, node *, node *);

    public:
    BPTree();
    void insert(key_tt);
    void search(string);
    node* getRoot();
    void display(node*);
};

BPTree::BPTree()
{
    root = NULL;
}

void BPTree::search(string x)
{

	if (root == NULL) {
		cout << "Tree is empty\n";
	}

	else {

		node* cursor = root;

		while (cursor->is_leaf == false) {

			for (int i = 0; i < cursor->size; i++) {

				if (x < cursor->keys[i].english) {
					cursor = cursor->ptr[i];
					break;
				}

				if (i == cursor->size - 1) {
					cursor = cursor->ptr[i + 1];
					break;
				}
			}
		}

		for (int i = 0; i < cursor->size; i++) {

			// If found then return
			if (cursor->keys[i].english == x) {
				cout << "Found\n";
                cout << cursor->keys[i].english<< "\t";
				cout << cursor->keys[i].bangla<<endl;
				return;
			}
		}
		cout << "Not found\n";
	}
}

void BPTree::insert(key_tt key)
{
    if(root == NULL)
    {
        //root is null, return newly created node
        root = new node;
        root->keys[0] = key;
        root->is_leaf = true;
        root->size = 1;
    }
    else
    {
        /* traverse the B+ tree */
        node *cursor = root;
        node *parent;
        // reach leaf node

        while(cursor->is_leaf == false)
        {
            parent = cursor;
            for(int i=0; i< cursor->size;i++)
            {
                //found the position where to insert
                if(key.english < cursor->keys[i].english)
                {
                    cursor = cursor->ptr[i];
                    break;
                }

                //if it reaches the end
                if(i == cursor->size - 1)
                {
                    cursor = cursor->ptr[i+1];
                    break;
                }
            }
        }

        if(cursor->size < MAX)
        {
            int i = 0;
            while(key.english > cursor->keys[i].english && i < cursor->size)
                i++;

            for(int j = cursor->size; j>i; j--)
            {
                cursor->keys[j] = cursor->keys[j-1];
            }

            cursor->keys[i] = key;
            cursor->size++;

            cursor->ptr[cursor->size] = cursor->ptr[cursor->size-1];
            cursor->ptr[cursor->size - 1] = NULL;
        }

        else
        {

            node* newLeaf = new node;
            key_tt virtualNode[MAX+2];

            for(int i=0; i<MAX; i++)
            {
                virtualNode[i] = cursor->keys[i];
            }

            int i=0,j;

            while(key.english > virtualNode[i].english && i<MAX)
            {
                i++;
            }

            for(int j=MAX+1; j>i ;j--)
            {
                virtualNode[j] = virtualNode[j-1];
            }
            virtualNode[i] = key;
            newLeaf->is_leaf =  true;

            cursor->size = (MAX+1)/2;
            newLeaf->size = MAX + 1 - (MAX+1)/2;
            cursor->ptr[newLeaf->size] = cursor->ptr[MAX];
            cursor->ptr[MAX] = NULL;

			for (i = 0; i < cursor->size; i++)
            {
				cursor->keys[i] = virtualNode[i];
			}

			for (i = 0, j = cursor->size;i < newLeaf->size; i++, j++) {
				newLeaf->keys[i] = virtualNode[j];
			}

			if (cursor == root) {
				node* newRoot = new node;

				newRoot->keys[0] = newLeaf->keys[0];
				newRoot->ptr[0] = cursor;
				newRoot->ptr[1] = newLeaf;
				newRoot->is_leaf = false;
				newRoot->size = 1;
				root = newRoot;
			}
			else
            {
				insertInternal(newLeaf->keys[0], parent, newLeaf);
			}
        }
    }
}

void BPTree::insertInternal(key_tt key, node* cursor, node* child)
{
    //no overflow
    if(cursor->size < MAX)
    {
        int i=0;

		while (key.english > cursor->keys[i].english && i < cursor->size)
        {
			i++;
		}

		for (int j = cursor->size; j > i; j--)
        {
			cursor->keys[j] = cursor->keys[j - 1];
		}

		for (int j = cursor->size + 1; j > i + 1; j--)
        {
			cursor->ptr[j] = cursor->ptr[j - 1];
		}

		cursor->keys[i] = key;
		cursor->size++;
		cursor->ptr[i + 1] = child;
    }
    else
    {
        node* newInternal = new node;
        key_tt virtualKey[MAX + 2];
        node* virtualPtr[ MAX + 2];

		for (int i = 0; i < MAX; i++)
        {
			virtualKey[i] = cursor->keys[i];
		}
		for (int i = 0; i < MAX + 1; i++) {
			virtualPtr[i] = cursor->ptr[i];
		}
		int i = 0, j;
		while (key.english > virtualKey[i].english && i < MAX)
        {
			i++;
		}
		for (int j = MAX + 1; j > i; j--) {
			virtualKey[j] = virtualKey[j - 1];
		}
		virtualKey[i] = key;
		for (int j = MAX + 2; j > i + 1; j--)
        {
			virtualPtr[j] = virtualPtr[j - 1];
		}

		virtualPtr[i + 1] = child;
		newInternal->is_leaf = false;

		cursor->size = (MAX + 1) / 2;

		newInternal->size = MAX - (MAX + 1) / 2;
		for (i = 0, j = cursor->size + 1; i < newInternal->size; i++, j++)
        {
			newInternal->keys[i] = virtualKey[j];
		}

		for (i = 0, j = cursor->size + 1; i < newInternal->size + 1; i++, j++)
        {
			newInternal->ptr[i] = virtualPtr[j];
		}
		if (cursor == root) {

			node* newRoot = new node;
			newRoot->keys[0] = cursor->keys[cursor->size];
			newRoot->ptr[0] = cursor;
			newRoot->ptr[1] = newInternal;
			newRoot->is_leaf = false;
			newRoot->size = 1;
			root = newRoot;
		}

        else {
			insertInternal(cursor->keys[cursor->size],findParent(root, cursor), newInternal);
		}
    }
}

node* BPTree::findParent(node* cursor, node* child)
{
	node* parent;

	// If cursor reaches the end of Tree
	if (cursor->is_leaf || (cursor->ptr[0])->is_leaf) {
		return NULL;
	}

	for (int i = 0; i < cursor->size + 1; i++) {

		if (cursor->ptr[i] == child) {
			parent = cursor;
			return parent;
		}

		else {
			parent = findParent(cursor->ptr[i], child);
			if (parent != NULL)
				return parent;
		}
	}

	return parent;
}

node* BPTree::getRoot()
{
	return root;
}

void input_and_run()
{
//    if (!setlocale(LC_CTYPE, "en_US.UTF-8"))
//    {
//        fprintf(stderr, "Can't set the specified locale! "
//                "Check LANG, LC_CTYPE, LC_ALL.\n");
//        return ;
//    }
    FILE* fp;
    BPTree tree;
    key_tt mykey;
    string toSearch;
    fp = fopen("Translation.txt", "r");
    char inputString[100];
    fgets(inputString, 100, fp);
    for(int i=0;i<5000;i++)
    {
        string english, bangla;
        fgets(inputString, 100, fp);
        int j=1;
        for(;inputString[j]!='|';j++){
            english += inputString[j];
        }
        j++;
        for(;j<strlen(inputString);j++){
            bangla += inputString[j];
        }
        if(i==2) toSearch = english;

        //cout<< bangla << ' ' << english;
        mykey.bangla = bangla;
        mykey.english = english;
        tree.insert(mykey);
    }

    while(cin>>toSearch)
    {
        tree.search(toSearch);
    }
}

int main()
{
    input_and_run();

    return 0;
}
