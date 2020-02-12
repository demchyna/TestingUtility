import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tester {

    public static boolean isInterface(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return clazz.isInterface();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isAbstractClass(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isClass(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return  !Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isEnum(String typeName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            return clazz.isEnum();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean extendsClass(String parentName, String childName) {
        try {
            Class<?> parentClazz = Class.forName(parentName);
            Class<?> childClazz = Class.forName(childName);
            return parentClazz.isAssignableFrom(childClazz) && (isClass(parentName) || isAbstractClass(parentName));
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean implementsInterface(String parentName, String childName) {
        try {
            Class<?> parentClazz = Class.forName(parentName);
            Class<?> childClazz = Class.forName(childName);
            return parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredPublicField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && Modifier.isPublic(field.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredProtectedField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && Modifier.isProtected(field.getModifiers())) {
                       return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredPrivateField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && Modifier.isPrivate(field.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredPackagePrivateField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())
                        && !Modifier.isPublic(field.getModifiers())
                        && !Modifier.isProtected(field.getModifiers())
                        && !Modifier.isPrivate(field.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredStaticField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && Modifier.isStatic(field.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredFinalField(String typeName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && Modifier.isFinal(field.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean inheritsField(String parentType, String childType, String fieldName) {
        try {
            Class<?> parentClazz = Class.forName(parentType);
            Class<?> childClazz = Class.forName(childType);
            Field[] parentFields = parentClazz.getDeclaredFields();
            Field[] childFields = childClazz.getDeclaredFields();
            return isFieldInClass(fieldName, parentFields)
                    && !isFieldInClass(fieldName, childFields)
                    && !hasDeclaredPrivateField(parentType, fieldName);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredPublicMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && Modifier.isPublic(method.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredProtectedMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && Modifier.isProtected(method.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredPrivateMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && Modifier.isPrivate(method.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredPackagePrivateMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())
                        && !Modifier.isPublic(method.getModifiers())
                        && !Modifier.isProtected(method.getModifiers())
                        && !Modifier.isPrivate(method.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredStaticMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && Modifier.isStatic(method.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasDeclaredFinalMethod(String typeName, String methodName) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && Modifier.isFinal(method.getModifiers())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean inheritsMethod(String parentType, String childType, String methodName) {
        try {
            Class<?> parentClazz = Class.forName(parentType);
            Class<?> childClazz = Class.forName(childType);
            Method[] parentMethods = isMethodInClass(methodName, parentClazz.getDeclaredMethods());
            Method[] childMethods = isMethodInClass(methodName, childClazz.getDeclaredMethods());
            if (parentMethods.length == 0) {
                return false;
            }
            for (Method parentMethod : parentMethods) {
                for (Method childMethod : childMethods) {
                    if (equalParamTypes(parentMethod.getParameterTypes(), childMethod.getParameterTypes())
                            || Modifier.isPrivate(parentMethod.getModifiers())) {
                        return false;
                    }
                }
                if (Modifier.isPrivate(parentMethod.getModifiers())) {
                    return false;
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean overridesMethod(String parentType, String childType, String methodName) {
        try {
            Class<?> parentClazz = Class.forName(parentType);
            Class<?> childClazz = Class.forName(childType);
            Method[] parentMethods = isMethodInClass(methodName, parentClazz.getDeclaredMethods());
            Method[] childMethods = isMethodInClass(methodName, childClazz.getDeclaredMethods());
            for (Method parentMethod : parentMethods) {
                for (Method childMethod : childMethods) {
                    if (equalParamTypes(parentMethod.getParameterTypes(), childMethod.getParameterTypes())
                            && !Modifier.isPrivate(parentMethod.getModifiers())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasFieldType(String typeName, String fieldName, Class<?> fieldType) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName()) && field.getType().equals(fieldType)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasMethodReturnType(String typeName, String methodName, Class<?> returnType) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName()) && method.getReturnType().equals(returnType)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasMethodParameterTypes(String typeName, String methodName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Class<?>[] types = method.getParameterTypes();
                if(Arrays.equals(types, parameterTypes)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean hasClassDeclaredConstructor(String typeName, Class<?>[] parameterTypes) {
        try {
            Class<?> clazz = Class.forName(typeName);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] types = constructor.getParameterTypes();
                if(Arrays.equals(types, parameterTypes)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean isFieldInClass(String fieldName, Field[] parentFields) {
        for (Field field : parentFields) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }


    private static Method[] isMethodInClass(String methodName, Method[] declaredMethods) {
        List<Method> methods = new LinkedList<>();
        for (Method method : declaredMethods) {
            if (methodName.equals(method.getName())) {
                methods.add(method);
            }
        }
        return methods.toArray(new Method[0]);
    }

    private static boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
        if (params1.length == params2.length) {
            for (int i = 0; i < params1.length; i++) {
                if (params1[i] != params2[i])
                    return false;
            }
            return true;
        }
        return false;
    }
}
