package cn.sysu.spring;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata.getAnnotationTypes().forEach(System.out::println);
        importingClassMetadata.getAnnotationAttributes("cn.sysu.spring.EnableStudent")
                .forEach((k, v) -> {
                    System.out.println("key: " + k + ", value: " + v);
                }); // 输出 key: teacherName, value: ph
        return new String[]{StudentConfig.class.getName()};
    }
}
