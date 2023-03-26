# Çoklu Bağlantılı Liste ile Sayı Düşürme Oyunu 
![resim](https://user-images.githubusercontent.com/75750279/227788717-1ef2ff2c-38aa-4952-a40b-9f7ad70416f3.png)


Bizden istenenler: 
	Bize belli bir sırada verilen sayı bloklarını istenen konuma sırayla düşürüp bunların gerekli işlemlerden geçerek çoklu bağlantılı liste modelini uygulamak.

Öğrendiklerimiz:
    • Çok boyutlu (yukarı, aşağı, sağ, sol) bağlantılı listeler nasıl oluşturulur,
    • Bu bağlantılar nasıl güncellenir,
    • Nasıl silinir,
    • Düğüm (Node) sınıfı nasıl yazılır,
    • Logaritma kullanılarak normalizasyon yaparak renk kodu nasıl üretilir,
Öğrendim.

Projeden bazı kesitler:
Tüm ekleme işlemleri burada gerçekleşiyor. Temel olarak listenin boş olup olmadığını kontrol edip boş ise yeni düğümü ekleniyor. Değilse gelen bloğun konumuna baştan sola doğru gidip eğer o konumda blok varsa üstüne ekleme fonksiyonu çalışıyor. Değilse soluna ekleniyor.
	Sonrasında üstteki bloklar ile aynı mı diye kontrol metoduna gidiyor.
```    
public void addProces(int data, int x) {
        Node<Integer> n_node = new Node<Integer>(data, x);
        Node<Integer> tmp = head;

        if (head == null) {
            head = n_node;
        } else {
            while (tmp.left != null && tmp.left.x <= x) {
                tmp = tmp.left;
            }
            if (tmp.x == x) {
                while (tmp.up != null) {
                    tmp = tmp.up;
                }
                addBlockH(tmp, n_node);
            } else {
                addBlockV(tmp, n_node);
            }
            controllBlock(n_node);
        }
        print();
    }
```



Yukarı doğru blok eklemek istersek solunu sağını ayarlayarak bloğu listeye bağlıyoruz
```    
      void addBlockH(Node<Integer> tmp, Node<Integer> n_node) {

        tmp.up = n_node;
        n_node.down = tmp;
        if (tmp.left != null && tmp.x == n_node.x)
            if (tmp.left.up != null) {
                tmp.left.up.right = n_node;
                n_node.left = tmp.left.up;
            }
        if (tmp.right != null && tmp.x == n_node.x)
            if (tmp.right.up != null) {
                tmp.right.up.left = n_node;
                n_node.right = tmp.right.up;
            }
    }
```

Yatay olarak bloğu eklemek için eğer konumu başlangıç düğümü (head)’den küçükse yeni başlangıç düğümü yapıp ekleme işlemini gerçekleştiriyoruz. Değilse direk soluna ekleyip bağlantıları yapıyoruz.
```    
    void addBlockV(Node<Integer> tmp, Node<Integer> n_node) {
        if (head.x > n_node.x) {
            n_node.left = head;
            head.right = n_node;
            head = n_node;
        } else {
            n_node.left = tmp.left;
            tmp.left = n_node;
            n_node.right = tmp;
        }
    }
```

Silme işlemi için ise çevresi kontrol edilip bağlantıları kaldırıp listeden koparılıyor.
```    
      void removeBlockAbove(Node<Integer> n_node) {
        if (n_node.left != null)
            n_node.left.right = null;
        if (n_node.right != null)
            n_node.right.left = null;
        if (n_node.down != null)
            n_node.down.up = null;
    }
```


Bu uygulama için veri tam sayı(Integer) seçildi ama istenirse liste ve düğümler istenilen veri yapısına göre eklenebilir tipte oluşturuldu (generic).
```
public class Node <T> {
    public T data; //? generic for blocks

    //? blocks dimensions
    public Node<T> right;
    public Node<T> left;
    public Node<T> up;
    public Node<T> down;
    public int x;

    public Node(T data,int x){
        //? defining vals
        this.data=data;
        this.right=null;
        this.left=null;
        this.up=null;
        this.down=null;
        this.x=x;
    }
}
```



Arayüz Kısmı için:
Her seferinde tekrardan uğraşmamak için JPanal türünde bir blok oluşturup uygulamada kullanıldı.
![resim](https://user-images.githubusercontent.com/75750279/227788524-dfe1d8d2-9bcc-4c4b-8ee8-dea8eb79eb8c.png)




Tüm blokları elle oluşturmaktansa burada kod ile arayüze eklendi ve boyut ve konum ayarları yapıldı.
```    
      private static void generateBlock() {
        int a_x = 100;
        int a_y = 150;
        int size = 41;
        for (int i = 0; i < x + 1; i++) {
            for (int j = 0; j < y + 1; j++) {
                block_l[i][j] = new Block();
                block_l[i][j].setSize(size, size);
                block_l[i][j].setLocation(a_x + i * (size + 10), a_y + j * (size + 10));
                frame.add(block_l[i][j]);
                block_l[i][j].setVisible(true);
            }
        }
    }
```


Gelen bağlantı listesi ve blok listesine göre arayüzde gerekli konumlamayı yapıyor. Bunun için başlangıç noktalarını alıp arayüze tersten ekliyor. Her seferinde blokları temizliyor ki eskiden kalan artıklar kalmasın.
```    
      private void updateBlock(MultiDimList<Integer> l, Block block_l[][]) {
        Node<Integer> tmp = l.head;
        Node<Integer> tmpH = l.head;
        int j = y;
        clearBlocks(block_l);
        while (tmpH != null) {
            while (tmp != null) {
                int x_co=x-tmp.x;
                int val = tmp.data;

                block_l[x_co][j].changeVal(val + "");
                block_l[x_co][j].setBackground(getColor(val));
                j--;
                tmp = tmp.up;
            }
            j = y;
            tmpH = tmpH.left;
            tmp = tmpH;
        }
    }
```

Her blok için teker teker renk tanımlaması yapmamak için ne yapılabilir diye düşünürken matematik hocalarımın bize  tekrar tekrar söylediği logaritmik fonksiyonların belli bir noktaya yakınlaşması ile ilgili grafik aklıma geldi onu kullanarak ne yapabilirim diye ararken böyle bir formül karşıma geldi. Biraz değiştirerek 2048’e sabitleyip en fazla 255 sayısının çıkmasına sağlayacak şeklide düzenlendi.
```    
      private Color getColor(int value) {
        int r, g, b;
        //? this mat formul give how close the value is to 2048 and do nomalization
        r = min(255, (int) (Math.log(value) / Math.log(2048) * 255) + 30);
        g = min(255, Math.abs((int) ((Math.log(value) - Math.log(81)) / (Math.log(2048) - Math.log(64)) * 255)));
        b = min(255, Math.abs((int) ((Math.log(value) - Math.log(41)) / (Math.log(2048) - Math.log(16)) * 255)));
        System.out.println(r + " " + g + " " + " " + b);
        return new Color(r, g, b);
    }
```

Bize verilen konum ve değerleri dizi içine alıp her seferinde biri eklenecek şekilde buton fonksiyonuna eklendi.
```        
        int index[] = { 4, 1, 3, 2, 0, 3, 0, 4,
                4, 3, 2, 2, 1, 3, 2, 4, 0, 2, 3, 1,
                1, 1, 1, 3, 2, 2, 2, 2, 2, 3, 3 };

        int vals[] = { 2, 2, 4, 2, 4, 2, 4, 8,
                8, 32, 2, 64, 16, 64, 32, 16, 16,
                32, 64, 8, 4, 2, 2, 2, 64, 32,
                16, 8, 8, 4, 8 };

        try {
            l.addProces(vals[count], index[count]);
            count++;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Game Over");
        }
        updateBlock(l, block_l);
```
