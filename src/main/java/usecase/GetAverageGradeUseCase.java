package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     *
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        final Team team = gradeDataBase.getMyTeam();
        final int count = team.getMembers().length;
        float sum = 0;

        for (String member : team.getMembers()) {
            final Grade grade = gradeDataBase.getGrade(member, course);
            sum += grade.getGrade();
        }

        if (count == 0) {
            return 0;
        }

        return sum / count;
    }
}
