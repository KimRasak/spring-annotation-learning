package cn.sysu.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "student")
public class StudentBean {

    /**
      student's name.
     */

    private String name;

    public StudentBean() {}

    public StudentBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Deprecated
    public void setId(String id) {
        this.name = id;
    }

    @DeprecatedConfigurationProperty(replacement = "student.name")
    @Deprecated
    public String getId() { return this.name; }
}
