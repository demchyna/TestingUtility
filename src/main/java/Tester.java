import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Tester {
    private ArrayList<Boolean> results;
    private int maxScore;

    public Tester(int maxScore) {
        results = new ArrayList<>();
        this.maxScore = maxScore;
    }

    public Tester isInterface(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for(String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = clazz.isInterface();
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public Tester isAbstractClass(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for (String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public Tester isClass(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for (String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = !Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public Tester isEnum(String[] typeNames) {
        Class<?> clazz;
        boolean predicate;
        for (String typeName : typeNames) {
            try {
                clazz = Class.forName(typeName);
                predicate = clazz.isEnum();
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public Tester extendsClass(String parentName, String[] childNames) {
        Class<?> childClazz, parentClazz;
        boolean predicate;
        for (String childName : childNames) {
            try {
                parentClazz = Class.forName(parentName);
                childClazz = Class.forName(childName);
                predicate = childClazz.getSuperclass().equals(parentClazz);
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public Tester implementsInterface(String parentName, String[] childNames) {
        Class<?> childClazz, parentClazz;
        boolean predicate;
        for (String childName : childNames) {
            try {
                parentClazz = Class.forName(parentName);
                childClazz = Class.forName(childName);
                predicate = parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface();
                results.add(predicate);
            } catch (ClassNotFoundException e) {
                results.add(false);
            }
        }
        return this;
    }

    public Tester hasDeclaredField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredPublicField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isPublic(field.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredProtectedField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isProtected(field.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredPrivateField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isPrivate(field.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredPackagePrivateField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName())
                            && !Modifier.isPublic(field.getModifiers())
                            && !Modifier.isProtected(field.getModifiers())
                            && !Modifier.isPrivate(field.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredStaticField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isStatic(field.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredFinalField(String typeName, String[] fieldNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                for (Field field : fields) {
                    if (fieldName.equals(field.getName()) && Modifier.isFinal(field.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester inheritsField(String parentType, String childType, String[] fieldNames) {
        Class<?> parentClazz, childClazz;
        try {
            parentClazz = Class.forName(parentType);
            childClazz = Class.forName(childType);

            Field[] parentFields = parentClazz.getDeclaredFields();
            Field[] childFields = childClazz.getDeclaredFields();

            for (String fieldName : fieldNames) {
                if (isFieldInParent(fieldName, parentFields) && isFieldInChild(fieldName, childFields)) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredPublicMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isPublic(method.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredProtectedMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isProtected(method.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredPrivateMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isPrivate(method.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredPackagePrivateMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName())
                            && !Modifier.isPublic(method.getModifiers())
                            && !Modifier.isProtected(method.getModifiers())
                            && !Modifier.isPrivate(method.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredStaticMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isStatic(method.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasDeclaredFinalMethod(String typeName, String[] methodNames) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                for (Method method : methods) {
                    if (methodName.equals(method.getName()) && Modifier.isFinal(method.getModifiers())) {
                        predicate = true;
                    }
                }
                if (predicate) {
                    results.add(true);
                } else {
                    results.add(false);
                }
                predicate = false;
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester inheritsMethod(String parentType, String childType, String[] methodNames) {
        Class<?> parentClazz, childClazz;
        try {
            parentClazz = Class.forName(parentType);
            childClazz = Class.forName(childType);

            Method[] parentMethods = parentClazz.getDeclaredMethods();
            Method[] childMethods = childClazz.getDeclaredMethods();

            for (String methodName : methodNames) {
                if (isMethodInParent(methodName, parentMethods) && isMethodInChild(methodName, childMethods)) {
                    results.add(true);
                } else {
                    results.add(false);
                }
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public Tester hasMethodReturnType(String typeName, String methodName, Class<?> returnType) {
        Class<?> clazz;
        boolean predicate = false;
        try {
            clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (methodName.equals(method.getName()) && method.getReturnType().equals(returnType)) {
                    predicate = true;
                }
            }
            if (predicate) {
                results.add(true);
            } else {
                results.add(false);
            }

        } catch (ClassNotFoundException e) {
            results.add(false);
        }
        return this;
    }

    public double calculate() {
        int result = 0;
        for (boolean value : results) {
            if (value) {
                result = result + 1;
            }
        }
        double normalizeResult = (double)result * maxScore / results.size();
        return new BigDecimal(normalizeResult).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private boolean isFieldInParent(String fieldName, Field[] parentFields) {
        for (Field field : parentFields) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isFieldInChild(String fieldName, Field[] childFields) {
        for (Field field : childFields) {
            if (fieldName.equals(field.getName())) {
                return false;
            }
        }
        return true;
    }

    private boolean isMethodInParent(String methodName, Method[] parentMethods) {
        for (Method method : parentMethods) {
            if (methodName.equals(method.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isMethodInChild(String methodName, Method[] childMethods) {
        for (Method method : childMethods) {
            if (methodName.equals(method.getName())) {
                return false;
            }
        }
        return true;
    }
}
