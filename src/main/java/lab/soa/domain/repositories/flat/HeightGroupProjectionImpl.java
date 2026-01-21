package lab.soa.domain.repositories.flat;

public class HeightGroupProjectionImpl implements HeightGroupProjection {
    private final Integer height;
    private final Long count;

    public HeightGroupProjectionImpl(Integer height, Long count) {
        this.height = height;
        this.count = count;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public Long getCount() {
        return count;
    }
}
