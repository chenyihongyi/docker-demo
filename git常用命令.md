touch README.md
git init
git status
git add .
git status
git commit -am '新建springcloud项目'
git remote add origin https://github.com/chenyihongyi/docker-demo.git
git branch
git pull
git push -u -f origin master (强制提交)
git branch
git branch -r
14. 创建分支
git checkout -b v1.0 origin/master
git branch
git push origin HEAD -u (推送到远程分支)


分支操作：

git branch 创建分支

git branch -b 创建并切换到新建的分支上

git checkout 切换分支

git branch 查看分支列表

git branch -v 查看所有分支的最后一次操作

git branch -vv 查看当前分支

git brabch -b 分支名 origin/分支名 创建远程分支到本地

git branch --merged 查看别的分支和当前分支合并过的分支

git branch --no-merged 查看未与当前分支合并的分支

git branch -d 分支名 删除本地分支

git branch -D 分支名 强行删除分支

git branch origin :分支名 删除远处仓库分支

git merge 分支名 合并分支到当前分支上


暂存操作：

git stash 暂存当前修改

git stash apply 恢复最近的一次暂存

git stash pop 恢复暂存并删除暂存记录

git stash list 查看暂存列表

git stash drop 暂存名(例：stash@{0}) 移除某次暂存

git stash clear 清除暂存


回退操作：

git reset --hard HEAD^ 回退到上一个版本

git reset --hard ahdhs1(commit_id) 回退到某个版本

git checkout -- file撤销修改的文件(如果文件加入到了暂存区，则回退到暂存区的，如果文件加入到了版本库，则还原至加入版本库之后的状态)

git reset HEAD file 撤回暂存区的文件修改到工作区


拉取、上传免密码：

git config --global credential.helper stor





