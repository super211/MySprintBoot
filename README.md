#"# MySpringBoot" 

#GIT IGNORE（.gitignore）  
>
	/bin/
	/.settings/
	/target/
	/syslog/
>
	或在需要的目录与文件上右键Team点击忽略就行

>## Ref 
>
	*.class
 
>##### Ignore Package Files
	*.jar
	*.war
	*.ear
 
>##### Ignore Maven generated target folders  
	target
 
>##### Ignore eclipse files 
	.project
	.classpath
	.settings
	.metadata

#GIT OPERATON
* Initiate the repository 

>	
	echo "# MySprintBoot" >> README.md
	git init
	git add README.md
	git commit -m "first commit"
	git remote add origin https://super211@github.com/super211/MySprintBoot.git [git remote rm origin]
	git push -u origin master

* Ignore the files but not remove them

>
	git rm -r --cached target

#Markdown File
* Editor

>
	Use "WikiText Editor" in Eclipse

* Online Tutorial

>
	http://xianbai.me/learn-md/index.html
	https://laravel-china.org/topics/621
	
#@Resource、@Autowired、@Qualifier

>
	@Resource = @Autowired+@Qualifier
>
	@Autowire 默认按照类型装配，默认情况下它要求依赖对象必须存在如果允许为null，可以设置它required属性为false，如果我们想使用按照名称装配，可以结合@Qualifier注解一起使用; 
>	
	@Resource默认按照名称装配，当找不到与名称匹配的bean才会按照类型装配，可以通过name属性指定，如果没有指定name属 性，当注解标注在字段上，即默认取字段的名称作为bean名称寻找依赖对象，当注解标注在属性的setter方法上，即默认取属性名作为bean名称寻找 依赖对象.
	注意：如果没有指定name属性，并且按照默认的名称仍然找不到依赖的对象时候，会回退到按照类型装配，但一旦指定了name属性，就只能按照名称装配了.

>
	注解注入顾名思义就是通过注解来实现注入，Spring和注入相关的常见注解有Autowired、Resource、Qualifier、Service、Controller、Repository、Component。
>	
	Autowired是自动注入，自动从spring的上下文找到合适的bean来注入
	Resource用来指定名称注入
	Qualifier和Autowired配合使用，指定bean的名称
	Service，Controller，Repository分别标记类是Service层类，Controller层类，数据存储层的类，spring扫描注解配置时，会标记这些类要生成bean。
	Component是一种泛指，标记类是组件，spring扫描注解配置时，会标记这些类要生成bean。
>	
	上面的Autowired和Resource是用来修饰字段，构造函数，或者设置方法，并做注入的。而Service，Controller，Repository，Component则是用来修饰类，标记这些类要生成bean。
