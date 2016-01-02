package io.dongyue.at15.tree.common.format;

/**
 * Created by at15 on 15-12-26.
 * <p/>
 * The meta information
 */
public class Meta {
    public static final String DEFAULT_SEP = "\t";
    public static final Integer PARTITION_INDEX = 0;
    public static final Integer START_INDEX = 1;
    public static final Integer END_INDEX = 2;
    public static final Integer COUNT_INDEX = 3;
    public static final Integer INDEX_PATH_INDEX = 4;

    private Integer partitionId;
    private Integer start;
    private Integer end;
    private Long count;
    private String indexPath;

    public static Meta parse(String line) {
        return parse(line, DEFAULT_SEP);
    }

    public static Meta parse(String line, String sep) {
        // TODO: catch invalid line and through right exception instead of null pointer
        String[] columns = line.split(sep);
        Meta meta = new Meta();
        meta.setPartitionId(columns[PARTITION_INDEX]);
        meta.setStart(columns[START_INDEX]);
        meta.setEnd(columns[END_INDEX]);
        if (columns.length > COUNT_INDEX) {
            meta.setCount(columns[COUNT_INDEX]);
        }
        if (columns.length > INDEX_PATH_INDEX) {
            meta.setIndexPath(columns[INDEX_PATH_INDEX]);
        }
        return meta;
    }

    public Integer[] getRange() {
        // FIXME: range is redundant, but can't just return {start,end};
        Integer[] range = {start, end};
        return range;
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public String getPartitionIdAsString() {
        return String.valueOf(partitionId);
    }

    public void setPartitionId(String partitionId) {
        // TODO: check if valueOf return null
        setPartitionId(Integer.valueOf(partitionId));
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(String start) {
        setStart(Integer.parseInt(start));
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(String end) {
        setEnd(Integer.parseInt(end));
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(String count) {
        setCount(Long.valueOf(count));
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    // TODO: allow use different separator
    public String withOutPartitionId() {
        String s = start + DEFAULT_SEP + end;
        if (count != null) {
            s = s + DEFAULT_SEP + count;
        }
        if (indexPath != null) {
            s = s + DEFAULT_SEP + indexPath;
        }
        return s;
    }

    @Override
    public String toString() {
        return partitionId + DEFAULT_SEP + withOutPartitionId();
    }

}
