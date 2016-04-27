package Specs.Smoke.TestData

/**
 * Created by E002183 on 4/25/2016.
 */
class SmokeTestData {

    String random = "_" + generateRandom();

    public String PROJECT_NAME = "Test-OMICIA-Project-" + random
    public String SHORT_FILE = "/src/main/resources/VCFs/GID_validation_examples.vcf"
    public String FOUR_EXOMES = "/src/main/resources/VCFs/EX024_hc_first1000.vcf.gz"
    public String GENE_LABEL = "Genome-Label-1 — External-ID-1"
    public String GENE_NAME = "CDK11A"
    public String GENE_SET_NAME = "Test Gene Set Name" + random
    public String DESCRIPTION = "Test Gene Set Description" + random
    public String GENE_SYMBOLS = "CDK11A ABCA4"
    public int GENE_COUNT = 5
    public int VARIANT_GENE = 3

    /*Panel Builder*/
    public String PANEL_NAME = "Test Panel Name" + random
    public String PANEL_DESCRIPTION = "Test panel Description" + random

    public List GENES_TO_BE_CHECKED = ["ATXN1", "AFG3L2"];
    public String GENES_TO_BE_ADDED_AS_SYMBOLS = "NOC2L PLEKHN1 SAMD11"

    /*Filtering protocol*/
    public String FILTERING_PROTOCOL_NAME = "Test Filtering Protocol Name" + random
    public String FILTERING_PROTOCOL_DESCRIPTION = "Test Filtering Protocol Description" + random

    /*Clinical Reporting*/
    public String PATIENT_ID = "Test Patient ID 123" + random
    public String GENOME_NAME_TO_BE_SELECTED = "Genome-Label-1-EX024-1 — External-ID-1"

    def generateRandom() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}