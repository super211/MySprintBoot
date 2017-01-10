"# MySpringBoot" 

#GIT IGNORE（.gitignore）
/bin/
/.settings/
/target/
/syslog/
或在需要的目录与文件上右键Team点击忽略就行

------Ref-------
*.class
 
----Package Files
*.jar
*.war
*.ear
 
----ignore Maven generated target folders
target
 
----ignore eclipse files
.project
.classpath
.settings
.metadata

#GIT OPERATON

echo "# MySprintBoot" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://super211@github.com/super211/MySprintBoot.git [git remote rm origin]
git push -u origin master

---------
git rm -r --cached target