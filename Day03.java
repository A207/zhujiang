
public class Day03 {

 public static void main(String[] args) {
  Army a = new Army(3);
  a.addWeapon(new Tank());
  a.addWeapon(new Flighter());
  a.addWeapon(new WarShip());
  a.attacAll();
  a.moveAll();
 }

}
interface Assaultable {
 abstract public void attack();
}
interface Mobile {
 abstract public void move();
}
abstract class Weapon implements Assaultable, Mobile {
}
class Tank extends Weapon {
 public void attack() {
  System.out.println("�ɹ�����");
 }
 public void move() {
  System.out.println("���ƶ���");
 }
}

class Flighter extends Weapon {

 public void attack() {
  System.out.println("Flighter ����");
 }

 public void move() {
  System.out.println("Flighter �ƶ�");
 }

}

class WarShip extends Weapon {

 public void attack() {
  System.out.println("WarShip ����");
 }

 public void move() {
  System.out.println("WarShip �ƶ�");
 }

}

class Army {
 private Weapon[] w = null;

 private int size = 0;

 private Army() {
 }

 public Army(int i) {
  w = new Weapon[i];
 }

 public void addWeapon(Weapon weapon) {
  if (size >= w.length) {
   System.out.println("����װ���㹻��");
   return;
  } else {
   w[size] = weapon;
   size++;
  }
 }

 public void attacAll() {
  for (Weapon wea : w) {
   if (wea != null) {
    wea.attack();
   }
  }
 }

 public void moveAll() {
  for (Weapon wea : w) {
   if (wea != null) {
    wea.move();
   }
  }
 }

}
