public class SaveOrderHistory {

    public static void saveOrderHistory(Order userHistory, FileService fileService) throws Exception {
        fileService.saveOrderHistory(userHistory);
    }
}
