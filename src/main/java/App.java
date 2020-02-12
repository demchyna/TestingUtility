public class App {
    public static void main(String[] args) {
//        System.out.println(Tester.overridesMethod("A", "B", "m"));
//        System.out.println(Tester.hasMethodParameterTypes("B", "m", new Class[]{double.class, int.class}));
//        System.out.println(Tester.extendsClass("B", "C"));
//        System.out.println(Tester.inheritsMethod("A", "C", "m"));

        System.out.println(Tester.inheritsField("A", "B", "b"));
    }
}

class A {
    int a;
    private int b;
}

class B extends A {
    int a;
}

class C extends B {
}
