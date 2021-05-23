# Git

## 配置用户信息

```powershell
git config --global user.name "{}"
git config --global user.email "{}"
```

修改

```powershell
git config --replace-all user.name "{}"
```

设置默认 editor
似乎必须用 vim

```powershell
git config --global core.editor vim
```
