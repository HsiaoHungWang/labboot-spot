## 建立範例資料庫
- 環境要先安裝好 SQL Server (範例資料庫使用版本 Microsoft SQL Server Developer (64-bit) 15.0.2000.5)
- 執行 ScriptWithTableData.sql，要自行先建立 MyDB 資料庫，此 Script 會建立資料表及匯入資料
## 在 VS Code 中安裝執行 Java 程式的擴充程式(Extensions)
- Java程式設計：Extension Pack for Java
- SpringBoot程式設計：Spring Boot Extension Pack
## 沒有JDK的下載 https://reurl.cc/WN9VWx

## 設定Extension Pack for Java：JDK位置 (要先安裝好 JDK)
- VSCode選單=>File=>Preferences=>Settings
- 搜尋列輸入："jdk"=>找出"Java > Jdt > Ls > Java: Home"
- 點選連結：Edit in settings.json
- 輸入java.jdt.ls.java.home 設定
```
{"java.jdt.ls.java.home":"C:\\作業系統目錄\\JDK安裝目錄"}
```
